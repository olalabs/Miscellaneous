using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using static System.Windows.Forms.VisualStyles.VisualStyleElement.Window;

namespace ProjectFilesCopier
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            FolderBrowserDialog folderBrowserDialog1 = new FolderBrowserDialog();

            // Display the openFile dialog.
            DialogResult result = folderBrowserDialog1.ShowDialog();

            String slnDirectoryPath = ""; 

            // OK button was pressed.
            if(result == DialogResult.OK)
            {
                slnDirectoryPath = folderBrowserDialog1.SelectedPath;
                
                String slnFilePath = Duplicator.FindSlnFile(slnDirectoryPath);

                if(slnFilePath != null)
                {
                    String contentSlnFile = Duplicator.GetSlnContent(slnFilePath);

                    List<String> allCsprojPaths = Duplicator.FindCsprojPath(contentSlnFile); //list of .csproj files 
                    List<string> allIncludeFiles = new List<string>();

                    foreach(var oneCsprojPath in allCsprojPaths)
                    {
                        //add to list all files .cs from all files .csproj 
                        allIncludeFiles.AddRange(Duplicator.GetCsprojContent(oneCsprojPath, slnDirectoryPath)); 
                    }

                    String pathOfCopy = Duplicator.MakeProjectCopyDirectory(slnDirectoryPath);

                    Duplicator.CopySlnFiles(slnFilePath, pathOfCopy);
                    Duplicator.CopyCsProjFiles(allCsprojPaths, slnDirectoryPath, pathOfCopy);
                    Duplicator.CopyIncludeFiles(allIncludeFiles, pathOfCopy, slnDirectoryPath);
                    Duplicator.MakeZip(pathOfCopy, slnDirectoryPath);

                    label3.Visible = true;
                }
            }
        }
    }
}
