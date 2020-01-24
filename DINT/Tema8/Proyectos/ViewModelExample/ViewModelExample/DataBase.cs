using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.ComponentModel;
using System.Data.Entity;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ViewModelExample
{
    
    public class DataBase
    {
        protected BDinformeEntities contexto { get; set; }
        
        public DataBase()
        {
            contexto = new BDinformeEntities();
            contexto.CLIENTES.Load();
        }

        public ObservableCollection<CLIENTES> DevuelveLista()
        {
            return contexto.CLIENTES.Local;
        }

        public void SaveChanges()
        {
            contexto.SaveChanges();
        }
        
    }
}
