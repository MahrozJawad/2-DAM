class Superheroe
{
    public string Nombre { get; set; }
    public string Enemigo { get; set; }
    public string Foto { get; set; }
    public bool Vengador { get; set; }


    public Superheroe()
    {
    }

    public static Superheroe GetSample()
    {
        Superheroe heroe = new Superheroe();
        heroe.Nombre = "Daredevil";
        heroe.Enemigo = "Kingping";
        heroe.Foto = @"https://dam.smashmexico.com.mx/wp-content/uploads/2018/08/27150601/daredevilbio_portada.jpg";
        heroe.Vengador = false;

        return heroe;
    }
}

