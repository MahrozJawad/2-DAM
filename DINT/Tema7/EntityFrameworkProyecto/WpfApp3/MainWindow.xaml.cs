using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Data.Entity;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace WpfApp3
{
    /// <summary>
    /// Lógica de interacción para MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        private CLIENTES cliente;
        BDinformeEntities contexto;
        public MainWindow()
        {
            InitializeComponent();
            contexto = new BDinformeEntities();
            contexto.CLIENTES.Load();
            ObservableCollection<CLIENTES> clientes = contexto.CLIENTES.Local;

            lista.DataContext = clientes;

        }

        private void Button_ClickInsertar(object sender, RoutedEventArgs e)
        {
            cliente = new CLIENTES()
            {
                id = Convert.ToInt32(IdentificadorInsertar),
                nombre = Convert.ToString(NombreInsertar),
                apellido = Convert.ToString(ApellidosInsertar),
            };
            contexto.CLIENTES.Add(cliente);
        }
        
    }
}
