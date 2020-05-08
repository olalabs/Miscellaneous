using System;
using System.Collections.Generic;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ResolutionChanger
{
    class Program
    {
        static void Main(string[] args)
        {
            Size resizedPicResolution = new Size();
            string inputDirPath = Directory.GetCurrentDirectory();
            string outputDirPath = Directory.GetCurrentDirectory() + "\\output";

            if (args.Length >=1 && args.Length <=3)
            {
                foreach(var param in args)
                {
                    string parameter = param.Substring(0, param.LastIndexOf('=')); 
                    if (parameter.Equals("-res"))
                    {
                        resizedPicResolution = GetResolution(param);
                    }
                    else if(parameter.Equals("-inputdir"))
                    {
                        inputDirPath = GetDirectory(param);
                    }
                    else if (parameter.Equals("-outputdir"))
                    {
                        outputDirPath = GetDirectory(param);
                    }
                }
            }
            else
            {
                Console.Write("Enter parameters!\nRequired: -res\nOptional: -inputdir -outputdir");
                Environment.Exit(0);
            }

            if (resizedPicResolution.IsEmpty)
            {
                Console.Write("-res parameter is required!");
                Environment.Exit(0);
            }

            Resizer resizer = new Resizer(inputDirPath, outputDirPath, resizedPicResolution);
            List<string> pictureFiles = resizer.SelectPictures();
            resizer.ResizePicture(pictureFiles);
        }


        private static Size GetResolution(string param) 
        {
            string tempSize = param.Substring(param.LastIndexOf('=') + 1);
            int width = Convert.ToInt32(tempSize.Substring(0, tempSize.LastIndexOf('x')));
            int height = Convert.ToInt32(tempSize.Substring(tempSize.LastIndexOf('x') + 1));

           
            Size resizedPicResolution = new Size(width, height); 
            return resizedPicResolution; 
  
        }

        private static string GetDirectory(string param)
        {
            string dirPath = param.Substring(param.LastIndexOf('=') + 1);
            if (!Directory.Exists(dirPath))
            {
                Console.Write("Directory does not exist!");
                Environment.Exit(0);
            }
            return dirPath;
        }
    }
}
