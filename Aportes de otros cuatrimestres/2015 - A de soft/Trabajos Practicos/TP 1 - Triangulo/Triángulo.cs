using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Triangulo
{
    public partial class Triángulo : Form
    {
        public Triángulo()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            
        }

        private void ObtenerTipo_click(object sender, EventArgs e)
        {
            ObtenerTipo();            
        }

        private void ObtenerTipo()
        {
            int a,b,c;

            if (IsNumeric(lado1.Text) && IsNumeric(lado2.Text) && IsNumeric(lado3.Text))
            {
                a = Convert.ToInt32(lado1.Text);
                b = Convert.ToInt32(lado2.Text);
                c = Convert.ToInt32(lado3.Text);

                if (Math.Abs(a - c) < b && b < (a + c))
                {
                    if (a == b)
                    {
                        if (b == c)
                            TIPO.Text = "Equilátero";
                        else
                            TIPO.Text = "Isósceles";
                    }
                    else
                    {
                        if (b != c && c != a)
                            TIPO.Text = "Escaleno";
                        else
                            TIPO.Text = "Isósceles";
                    }
                }
                else
                    TIPO.Text = "La combinación ingresada no forma triángulo";
            }
            else
            {
                TIPO.Text = "Uno de los lados ingresados no es un valor entero";
            }

            
        }

        public static Boolean IsNumeric(string valor)
        {
            int result;
            return int.TryParse(valor, out result);
        }
    }
}
