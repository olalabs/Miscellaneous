using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Xml;
using System.Xml.Schema;
using System.IO.Compression;

namespace ProjectFilesCopier
{
    
    class Duplicator
    {     
        public static String FindSlnFile(String slnDirectoryPath)
        {
            if(Directory.Exists(slnDirectoryPath))
            {
                string[] allFiles = Directory.GetFiles(slnDirectoryPath);

                foreach(String oneFile in allFiles)
                {
                    if(oneFile.Contains(".sln"))
                    {
                        return oneFile;
                    }
                }
            }
            return null;
        }

        public static String GetSlnContent(String slnFilePath)
        {
            String contentSlnFile = File.ReadAllText(slnFilePath);

            return contentSlnFile;
        }

        public static List<String> FindCsprojPath(String contentSlnFile)
        {
            List<string> allCsprojPaths = new List<string>();

            String[] elements = contentSlnFile.Split(' ');
            foreach(var element in elements)
            {
                if(element.Contains(".csproj"))
                {
                    String withoutQuotesCsprojPath = element.Remove(0, 1); // remove first "
                    withoutQuotesCsprojPath = withoutQuotesCsprojPath.Remove(withoutQuotesCsprojPath.Length - 2); //remove last ",

                    allCsprojPaths.Add(withoutQuotesCsprojPath);
                }
            }
            return allCsprojPaths;
        }

        public static List<String> GetCsprojContent(String csProjPath, String slnDirectoryPath)
        {
            List<string> allIncludeFiles = new List<string>();

            String concatCsProjPath = slnDirectoryPath + "\\" + csProjPath;
            XmlReader reader = null;
            ValidationEventHandler eventHandler = new ValidationEventHandler(ValidationCallback);

            try
            {
                // Create the validating reader and specify DTD validation.
                XmlReaderSettings settings = new XmlReaderSettings();
                settings.DtdProcessing = DtdProcessing.Parse;

                settings.ValidationEventHandler += eventHandler;

                reader = XmlReader.Create(concatCsProjPath, settings);

                // Pass the validating reader to the XML document.
                // Validation fails due to an undefined attribute, but the data is still loaded into the document.
                XmlDocument doc = new XmlDocument();
                doc.Load(reader);

                XmlNodeList[] includeElements = new XmlNodeList[3]; 
                includeElements[0] =  doc.GetElementsByTagName("Compile");
                includeElements[1] = doc.GetElementsByTagName("None");
                includeElements[2] = doc.GetElementsByTagName("EmbeddedResource");

                foreach(XmlNodeList tagElement in includeElements) // nodes in tagName compile, then none and then embeddedResource
                {
                    foreach(XmlNode xmlNode in tagElement) 
                    {
                        //folderName in which the subfolders (Properties etc.) will be created
                        String fragmentPath = csProjPath.Substring(0, csProjPath.LastIndexOf("\\"));  

                        allIncludeFiles.Add(fragmentPath + "\\" + xmlNode.Attributes["Include"].Value); //add files (.cs etc.)   

                    }
                }
            }
            finally
            {
                if(reader != null)
                    reader.Close();
            }
            return allIncludeFiles;
        }

        // Display the validation error.
        private static void ValidationCallback(object sender, ValidationEventArgs args)
        {
            Console.WriteLine(args.Message);
        }

        public static String MakeProjectCopyDirectory(String slnDirectoryPath)
        {
            string pathOfCopy = slnDirectoryPath + "\\ProjectCopy";

            try
            {
                if(Directory.Exists(pathOfCopy))
                {
                    return pathOfCopy;
                }
                // Try to create the directory.
                DirectoryInfo di = Directory.CreateDirectory(pathOfCopy);
            }
            catch (Exception e)
            {
                Console.WriteLine("The process failed: {0}", e.ToString());
            }
            finally { }

            return pathOfCopy;
        }

        public static void CopySlnFiles(String slnFilePath, String pathOfCopy)
        {
            String fileName = slnFilePath.Split('\\').Last(); 
            String pathOfCopyWithFilename = pathOfCopy + "\\" + fileName; 

            if(!File.Exists(pathOfCopyWithFilename))
            {
                File.Copy(slnFilePath, pathOfCopyWithFilename);  //source, destination
            }
        }

        public static void CopyCsProjFiles(List<string> allCsprojPaths, String slnDirectoryPath, String pathOfCopy) 
        {
            foreach(String oneCsprojFile in allCsprojPaths)
            {
                String directoryForCsproj = pathOfCopy + "\\" + 
                    oneCsprojFile.Substring(0, oneCsprojFile.LastIndexOf("\\"));
                //create a subdirectory in the copy directory 
                Directory.CreateDirectory(directoryForCsproj); 

                String fileToCopy = slnDirectoryPath + "\\" + oneCsprojFile;
                //get filename (without directory name) 
                String csprojFilename = oneCsprojFile.Split('\\').Last(); 
                String pathOfCopyWithFilename = directoryForCsproj + "\\" + csprojFilename; 


                if(!File.Exists(pathOfCopyWithFilename))
                {
                    File.Copy(fileToCopy, pathOfCopyWithFilename);
                }
            }
        }

        public static void CopyIncludeFiles(List<string> allIncludeFiles, String pathOfCopy, String slnDirectoryPath)
        {
            foreach(var oneIncludeFile in allIncludeFiles)  
            {
                //pathOfCopy + folder name with deleted file name 
                String subDirectory = pathOfCopy + "\\" + 
                    oneIncludeFile.Substring(0,oneIncludeFile.LastIndexOf("\\")); 
                Directory.CreateDirectory(subDirectory); //creating subfolders e.g. Properties

                String includeFileName = oneIncludeFile.Split('\\').Last();
                String pathOfCopyWithFilename = subDirectory + "\\" + includeFileName; 

                if(!File.Exists(pathOfCopyWithFilename))
                {
                    //source, destination (path as in source - only additional Copy before subfolder)
                    File.Copy(slnDirectoryPath + "\\" + oneIncludeFile, pathOfCopyWithFilename);
                }
            }
        }

        public static void MakeZip(String pathOfCopy, String slnDirectoryPath)
        {
            string zipPath = slnDirectoryPath + "\\ProjectCopy.zip";
            if(!File.Exists(zipPath))
            {
                ZipFile.CreateFromDirectory(pathOfCopy, zipPath);
            }
        } 
    }
}
