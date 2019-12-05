using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

class Jugador
{
    public int Dorsal { get; set; }
    public string Posicion { get; set; }
    public string Foto { get; set; }
    public string Nombre { get; set; }


    public Jugador(int dorsal, string posicion, string foto, string nombre)
    {
        Dorsal = dorsal;
        Posicion = posicion;
        Foto = foto;
        Nombre = nombre;
    }

    public static List<Jugador> GetJugadores()
    {
        List<Jugador> lista = new List<Jugador>();

        lista.Add(new Jugador(0, "Power Forward", "0.png", "Kyle Kuzma"));
        lista.Add(new Jugador(7, "Center", "7.png", "Javale McGee"));
        lista.Add(new Jugador(9, "Point Guard", "9.png", "Rajon Rondo"));
        lista.Add(new Jugador(3, "Power Forward", "3.png", "Anthony Davis"));
        lista.Add(new Jugador(39, "Center", "39.png", "Dwight Howard"));
        lista.Add(new Jugador(23, "Shooting Guard", "23.png", "Lebron James"));
        lista.Add(new Jugador(14, "Small Forward", "14.png", "Danny Green"));

        return lista;
    }

    
    public void NotifyPropertyChanged(string propertyName)
    {
        this.PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(propertyName));
    }
}

