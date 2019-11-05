
package com.controller;

import com.modelos.Conectar;
import com.modelos.Usuarios;
import com.modelos.UsuariosValidar;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("edit.htm")
public class EditController {

    UsuariosValidar usuariosValidar;
    private JdbcTemplate jdbcTemplate;
    
    public EditController() {
        this.usuariosValidar = new UsuariosValidar();
        Conectar con = new Conectar();
        this.jdbcTemplate = new JdbcTemplate(con.conectar());
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public  ModelAndView form(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        int id =  Integer.parseInt(request.getParameter("id"));
        Usuarios datos = this.selectUsuario(id);
        mav.setViewName("edit");
        mav.addObject("usuarios", new Usuarios(id,datos.getNombre(), datos.getCorreo()));
        return mav;
    }
    
    @RequestMapping(method=RequestMethod.POST)
    public ModelAndView form(@ModelAttribute("usuarios") Usuarios usuario,
                                BindingResult result,
                                SessionStatus status,
                                HttpServletRequest request) {
    this.usuariosValidar.validate(usuario, result);
    int id = Integer.parseInt(request.getParameter("id"));
        if(result.hasErrors()){
            ModelAndView mav = new ModelAndView();
            mav.setViewName("edit");
            mav.addObject("usuarios", new Usuarios());
            return  mav;
        } else {
            this.jdbcTemplate.update("update usuarios set nombre=?,correo=? where id=?",
                                     usuario.getNombre(),usuario.getCorreo(),id);
            return new ModelAndView("redirect:/home.htm");
        }
    } 

    private Usuarios selectUsuario(int id) {

        final Usuarios user = new Usuarios();
        String sql = "SELECT * FROM usuarios where id = '"+id+"'";
        return (Usuarios) jdbcTemplate.query(sql, new ResultSetExtractor<Usuarios>(){
            public Usuarios extractData(ResultSet rs) throws SQLException, DataAccessException {
                if (rs.next()) {
                    user.setNombre(rs.getString("nombre"));
                    user.setCorreo(rs.getString("correo"));
                }
                return user;
            }
        });
    }
}
