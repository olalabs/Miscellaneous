namespace NamesFilter
{
    partial class Form1
    {
        /// <summary>
        /// Wymagana zmienna projektanta.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Wyczyść wszystkie używane zasoby.
        /// </summary>
        /// <param name="disposing">prawda, jeżeli zarządzane zasoby powinny zostać zlikwidowane; Fałsz w przeciwnym wypadku.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Kod generowany przez Projektanta formularzy systemu Windows

        /// <summary>
        /// Metoda wymagana do obsługi projektanta — nie należy modyfikować
        /// jej zawartości w edytorze kodu.
        /// </summary>
        private void InitializeComponent()
        {
            this.openFile = new System.Windows.Forms.Button();
            this.inputText = new System.Windows.Forms.TextBox();
            this.result = new System.Windows.Forms.TextBox();
            this.time_label = new System.Windows.Forms.Label();
            this.time_loading = new System.Windows.Forms.Label();
            this.SuspendLayout();
            // 
            // openFile
            // 
            this.openFile.Font = new System.Drawing.Font("Consolas", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(238)));
            this.openFile.Location = new System.Drawing.Point(33, 33);
            this.openFile.Name = "openFile";
            this.openFile.Size = new System.Drawing.Size(189, 55);
            this.openFile.TabIndex = 0;
            this.openFile.Text = "Load file";
            this.openFile.UseVisualStyleBackColor = true;
            this.openFile.Click += new System.EventHandler(this.openFile_Click);
            // 
            // inputText
            // 
            this.inputText.Location = new System.Drawing.Point(33, 123);
            this.inputText.Name = "inputText";
            this.inputText.Size = new System.Drawing.Size(280, 20);
            this.inputText.TabIndex = 1;
            this.inputText.Tag = "";
            this.inputText.TextChanged += new System.EventHandler(this.inputText_TextChanged);
            // 
            // result
            // 
            this.result.Location = new System.Drawing.Point(452, 33);
            this.result.Multiline = true;
            this.result.Name = "result";
            this.result.ReadOnly = true;
            this.result.ScrollBars = System.Windows.Forms.ScrollBars.Vertical;
            this.result.Size = new System.Drawing.Size(282, 373);
            this.result.TabIndex = 2;
            // 
            // time_label
            // 
            this.time_label.AutoSize = true;
            this.time_label.Font = new System.Drawing.Font("Consolas", 14.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(238)));
            this.time_label.Location = new System.Drawing.Point(29, 193);
            this.time_label.Name = "time_label";
            this.time_label.Size = new System.Drawing.Size(50, 22);
            this.time_label.TabIndex = 3;
            this.time_label.Text = "TIME";
            // 
            // time_loading
            // 
            this.time_loading.AutoSize = true;
            this.time_loading.Font = new System.Drawing.Font("Consolas", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(238)));
            this.time_loading.Location = new System.Drawing.Point(30, 227);
            this.time_loading.Name = "time_loading";
            this.time_loading.Size = new System.Drawing.Size(241, 13);
            this.time_loading.TabIndex = 4;
            this.time_loading.Text = "Select file to view the operation time.";
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.SystemColors.ActiveCaption;
            this.ClientSize = new System.Drawing.Size(800, 450);
            this.Controls.Add(this.time_loading);
            this.Controls.Add(this.time_label);
            this.Controls.Add(this.result);
            this.Controls.Add(this.inputText);
            this.Controls.Add(this.openFile);
            this.Name = "Form1";
            this.Text = "Surname Filter";
            this.Load += new System.EventHandler(this.Form1_Load);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Button openFile;
        private System.Windows.Forms.TextBox inputText;
        private System.Windows.Forms.TextBox result;
        private System.Windows.Forms.Label time_label;
        private System.Windows.Forms.Label time_loading;
    }
}

