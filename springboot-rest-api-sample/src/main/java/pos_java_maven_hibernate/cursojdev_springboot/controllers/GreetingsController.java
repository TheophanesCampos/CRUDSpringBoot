package pos_java_maven_hibernate.cursojdev_springboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
    
//    @RequestMapping(value = "/{nome}", method =  RequestMethod.GET)
//    @ResponseStatus(HttpStatus.OK)
//    public String retornaOlaMundo (@PathVariable String nome) {
//    	/*testar injeção de dependecia salvando usuário*/
//    	Usuario usuario = new Usuario();
//    	usuario.setNome(nome);
//    	usuarioRepository.save(usuario);/*gravar no banco de dados*/
//    	
//    	return "Olá mundo! Bem vindo " + nome +"!";
//    }
    
//Primeiro método de API - buscarTodos   
    @GetMapping(value="listatodos")
    @ResponseBody /*retorna os dados para o corpo da resposta*/
    public ResponseEntity<List<Usuario>> listaUsuario(){
    	List<Usuario> usuarios = usuarioRepository.findAll(); /*executa a consulta no banco de dados*/
    	return new ResponseEntity<List<Usuario>>(usuarios,HttpStatus.OK);/*retorna a lista em json*/
    }
    
    @PostMapping(value = "salvar") /*Mapeia a url*/
    @ResponseBody /*Descrição da resposta*/
    public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario){/*Recebe os dados para salvar*/
    	Usuario user = usuarioRepository.save(usuario);
    	return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
    	
    }
    
    @PutMapping(value = "atualizar") /*Mapeia a url*/
    @ResponseBody /*Descrição da resposta*/
    public ResponseEntity<?> atualizar(@RequestBody Usuario usuario){/*Recebe os dados para salvar - ? significa que o retorno é genérico*/
    	if(usuario.getId()==null) {
    		return new ResponseEntity<String>("O id não foi informado", HttpStatus.BAD_REQUEST);	
    	}
    	Usuario user = usuarioRepository.saveAndFlush(usuario);
    	return new ResponseEntity<Usuario>(user, HttpStatus.OK);
    	
    }
    
    @DeleteMapping(value = "delete") /*Mapeia a url*/
    @ResponseBody /*Descrição da resposta*/
    public ResponseEntity<String> delete(@RequestParam Long iduser){/*Recebe o id do usuário a ser deletado*/
    	usuarioRepository.deleteById(iduser);
    	return new ResponseEntity<String>("User " + iduser + " deletado com sucesso", HttpStatus.OK);
    	
    }
    
    
    @GetMapping(value = "buscarpornome") /*Mapeia a url*/
    @ResponseBody /*Descrição da resposta*/
    public ResponseEntity<List<Usuario>> buscarPorNome(@RequestParam(name = "name") String name){/*Recebe os dados do usuário a ser consultado*/
    	
    	List<Usuario> usuarios = usuarioRepository.buscarPorNome(name.trim().toUpperCase());
    	
    	return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);	
    }
    
    
    @GetMapping(value = "buscaruserid") /*Mapeia a url*/
    @ResponseBody /*Descrição da resposta*/
    public ResponseEntity<Usuario> buscarUserId(@RequestParam(name = "iduser") Long iduser){/*Recebe os dados do usuário a ser consultado*/
    	Usuario usuario = usuarioRepository.findById(iduser).get();
    	return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);	
    }
    
    
}
