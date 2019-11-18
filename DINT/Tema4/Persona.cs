
namespace Personas {
    public class Persona
    {
        public int Id { get; set; }
        public string Nombre { get; set; }
        public string Foto { get; set; }
        public string Sexo { get; set; }

        public Persona(int id, string nombre, string foto, string sexo)
        {
            _id = id;
            _nombre = nombre;
            _foto = foto;
            _sexo = sexo;
        }

        public static ObservableCollection<Persona> GetPersonas()
        {
            ObservableCollection<Persona> lista = new ObservableCollection<Persona>();

            lista.Add(new Persona(1, "Pepe", "Imagenes/1.jpg", "Hombre"));
            lista.Add(new Persona(2, "Antonio", "Imagenes/2.jpg", "Hombre"));
            lista.Add(new Persona(3, "Sara", "Imagenes/3.jpg", "Mujer"));

            return lista;
        }
    }

}
