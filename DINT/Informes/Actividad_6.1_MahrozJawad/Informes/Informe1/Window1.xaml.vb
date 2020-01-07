Class Window1

    Public Sub New()

        ' This call is required by the designer.
        InitializeComponent()

        ' Add any initialization after the InitializeComponent() call.

        reportViewer1.Owner = Me
        reportViewer2.Owner = Me
        reportViewer3.Owner = Me
        reportViewer4.Owner = Me
        reportViewer5.Owner = Me

        Dim informe1 As New CrystalReport1
        Dim informe2 As New CrystalReport2
        Dim informe3 As New CrystalReport3
        Dim informe4 As New CrystalReport4
        Dim informe5 As New CrystalReport5

        informe1.SetDatabaseLogon("administrador", "admin123.")
        informe2.SetDatabaseLogon("administrador", "admin123.")
        informe3.SetDatabaseLogon("administrador", "admin123.")
        informe4.SetDatabaseLogon("administrador", "admin123.")
        informe5.SetDatabaseLogon("administrador", "admin123.")

        reportViewer1.ViewerCore.ReportSource = informe1
        reportViewer2.ViewerCore.ReportSource = informe2
        reportViewer3.ViewerCore.ReportSource = informe3
        reportViewer4.ViewerCore.ReportSource = informe4
        reportViewer5.ViewerCore.ReportSource = informe5

    End Sub
End Class
