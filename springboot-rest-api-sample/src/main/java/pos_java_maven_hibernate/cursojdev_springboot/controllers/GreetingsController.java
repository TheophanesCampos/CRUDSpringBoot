package pos_java_maven_hibernate.cursojdev_springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import pos_java_maven_hibernate.cursojdev_springboot.model.Usuario;
import pos_java_maven_hibernate.cursojdev_springboot.repository.UsuarioRepository;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class GreetingsController {
	@Autowired /* IC/CD ou CDI - Injeção de dependencia*/
	private UsuarioRepository usuarioRepository;
    /**
     *
     * @param name the name to greet
     * @return greeting text
     */
//    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
//    @ResponseStatus(HttpStatus.OK)
//    public String greetingText(@PathVariable String name) {
//        return "Curso Spring Boot API: " + name + "!!";
//	}
    
    @RequestMapping(value = "/{nome}", method =  RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String retornaOlaMundo (@PathVariable String nome) {
    	Usuario usuario = new Usuario();
    	usuario.setNome(nome);
    	usuarioRepository.save(usuario);/*gravar no banco de dados*/
    	
    	return "Olá mundo! Bem vindo " + nome +"!";
    }
}
