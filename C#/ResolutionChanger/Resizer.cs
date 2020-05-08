using System;
using System.Collections.Generic;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ResolutionChanger
{
    class Resizer
    {
        private string inputDirPath;
        private string outputDirPath;
        private Size resizedPicResolution; 
        
        public Resizer(string input, string output, Size res)
        {
            this.inputDirPath = input;
            this.outputDirPath = output;
            this.resizedPicResolution = res;

            if (outputDirPath.Equals(Directory.GetCurrentDirectory() + "\\output"))
            {
                Directory.CreateDirectory(outputDirPath);
            }

        }

        public List<string> SelectPictures()
        {
            List<string> pictureFiles = Directory.GetFiles(inputDirPath).ToList();

            int allFiles = pictureFiles.Count - 1;

            while (allFiles > 0)
            {
                string fileExtention = Path.GetExtension(pictureFiles[allFiles]);

                if (!(fileExtention.Equals(".jpg") || fileExtention.Equals(".bmp")))
                {
                    pictureFiles.RemoveAt(allFiles);
                }

                allFiles--;
            }

            return pictureFiles; 
        }

        public void ResizePicture(List<string> pictureFiles)
        {
            foreach (string picturePath in pictureFiles)
            {
                Bitmap originalPic = (Bitmap)Image.FromFile(picturePath);
                Bitmap resized = new Bitmap(originalPic, resizedPicResolution);

                string copyPicName = GeneratePicName(picturePath);

                resized.Save(copyPicName);
            }
        }

        private string GeneratePicName(string picturePath)
        {
            string copyPicName = outputDirPath + "\\" + Path.GetFileName(picturePath);

            int number = 1;
            while (File.Exists(copyPicName))
            {
                copyPicName = outputDirPath + "\\" +
                    Path.GetFileNameWithoutExtension(picturePath) +
                    "_" + number + Path.GetExtension(picturePath);
                number++;
            }

            return copyPicName;
        }

    }
}
