using System.Collections.ObjectModel;
using System.ComponentModel;
using System.Data.Entity;

namespace ViewModelExample
{
    public class MainWindowVM : INotifyPropertyChanged
    {
        DataBase db;
        public CLIENTES Cliente { get; set; }
        public ObservableCollection<CLIENTES> Clientes { get; set; }
        public CLIENTES ClienteSeleccionado { get; set; }
        public MainWindowVM()
        {
            db = new DataBase();
            Clientes = db.DevuelveLista();
        }

        public event PropertyChangedEventHandler PropertyChanged;

        public void Modificar()
        {
            db.SaveChanges();
        }
    }
}