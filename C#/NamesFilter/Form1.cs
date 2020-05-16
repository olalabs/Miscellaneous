using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Diagnostics;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Runtime.InteropServices;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace NamesFilter
{

    public partial class Form1 : Form
    {
        private Dictionary<string, List<string>> twoLetters = new Dictionary<string, List<string>>();
        private Dictionary<string, List<string>> threeLetters = new Dictionary<string, List<string>>();
        private List<string> fileContent;

        [DllImport("user32.dll")]
        static extern bool HideCaret(IntPtr hWnd);

        public Form1()
        {
            InitializeComponent();
        }

        private void openFile_Click(object sender, EventArgs e)
        {
        
             var filePath = string.Empty;

            using (OpenFileDialog openFileDialog = new OpenFileDialog())
            {
                //openFileDialog.InitialDirectory = "c:\\";
                openFileDialog.Filter = "txt files (*.txt)|*.txt";
                openFileDialog.FilterIndex = 2;
                openFileDialog.RestoreDirectory = true;

                if (openFileDialog.ShowDialog() == DialogResult.OK)
                {
                    if (!string.IsNullOrEmpty(openFileDialog.FileName))
                    {
                        //Get the path of specified file
                        filePath = openFileDialog.FileName;
                    }
                }
            }


            Stopwatch stopWatch = new Stopwatch();
            stopWatch.Start();
            if (File.Exists(filePath))
            {
                fileContent = File.ReadAllLines(filePath, Encoding.Default).ToList();
            }
            stopWatch.Stop();


            // Get the elapsed time as a TimeSpan value.
            TimeSpan ts = stopWatch.Elapsed;

            // Format and display the TimeSpan value.
            string elapsedTime = String.Format("{0:00}:{1:00}:{2:00}.{3:00}",
                ts.Hours, ts.Minutes, ts.Seconds,
                ts.Milliseconds / 10);
            time_loading.Text = "Reading file: " + elapsedTime;

            stopWatch.Start();
            AddWords(2, twoLetters);
            stopWatch.Stop();

            ts = stopWatch.Elapsed;
            elapsedTime = String.Format("{0:00}:{1:00}:{2:00}.{3:00}",
                ts.Hours, ts.Minutes, ts.Seconds,
                ts.Milliseconds / 10);
            time_loading.Text = time_loading.Text + 
                "\nGenerating structure for two-letter beginnings: " + elapsedTime;

            stopWatch.Start();
            AddWords(3, threeLetters);
            stopWatch.Stop();

            ts = stopWatch.Elapsed;
            elapsedTime = String.Format("{0:00}:{1:00}:{2:00}.{3:00}",
                ts.Hours, ts.Minutes, ts.Seconds,
                ts.Milliseconds / 10);
            time_loading.Text = time_loading.Text +  
                "\nGenerating structure for three-letter beginnings: " + elapsedTime;
        }


        private void AddWords(int numberOfLetters, Dictionary<string, List<string>> dictionary)
        {
            if (fileContent != null)
            {
                foreach (var element in fileContent)
                {
                    var name = element.Substring(element.LastIndexOf(' ') + 1);
                    if (name.Length >= numberOfLetters)
                    {
                        string shortName = name.Substring(0, numberOfLetters);

                        if (dictionary.ContainsKey(shortName))
                        {
                            dictionary[shortName].Add(name);
                        }
                        else
                        {
                            var begins = new List<string>();
                            begins.Add(name);
                            dictionary[shortName] = begins;
                        }
                    }
                }
            }
           
        }

        private void inputText_TextChanged(object sender, EventArgs e)
        {

            if(inputText.Text.Length >= 3)
            {
                //case insensitive
                var threeLet = inputText.Text.Substring(0, 3).ToLower();
                threeLet = threeLet.Substring(0, 1).ToUpper() + threeLet.Substring(1); 
                
                if (threeLetters.ContainsKey(threeLet))
                {
                    //  result.Text = twoLetters[twoLet][0]; //the most popular surname

                    StringBuilder stringBuilder = new StringBuilder();

                    foreach (var element in threeLetters[threeLet])
                    {
                        stringBuilder.Append(element);
                        stringBuilder.Append(Environment.NewLine);
                    }

                    result.Text = stringBuilder.ToString();
                }
            }
            else if (inputText.Text.Length >= 2)
            {
                var twoLet = inputText.Text.Substring(0, 2).ToLower();
                twoLet = twoLet.Substring(0, 1).ToUpper() + twoLet.Substring(1);
                if (twoLetters.ContainsKey(twoLet))
                {
                    StringBuilder stringBuilder = new StringBuilder();

                    foreach (var element in twoLetters[twoLet])
                    {
                        stringBuilder.Append(element);
                        stringBuilder.Append(Environment.NewLine);
                    }

                    result.Text = stringBuilder.ToString(); 
                }
            }
        }



        private void Form1_Load(object sender, EventArgs e)
        {
            //lambda for readOnlyTextBox without disabling textbox
            result.GotFocus += (s1, e1) => { HideCaret(result.Handle); };
            result.Cursor = Cursors.Arrow;
        }
    }
}



