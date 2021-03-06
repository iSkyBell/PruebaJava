package api.rest.indra.services;

import api.rest.indra.domain.Persona;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonaServicesTest {
    
    private Persona persona;
    private Persona nuevaPersona;
    
    private static final Long ID_REMOVER_TEST = 4L;
    private static final Long ID_ACTUALIZA_CONSULTA_TEST = 1L;
    
    private static final int ID_CONSULTA_UNO_TEST = 6;
    private static final int ID_CONSULTA_DOS_TEST = 5;
    
    private static final String SET_DATA_NOMBRE = "Dan";
    private static final String SET_DATA_APELLIDO = "Brown";
    private static final String SET_DATA_CEDULA = "1234";
    private static final String SET_DATA_GENERO = "M";
    private static final int SET_DATA_EDAD = 40;

    
    @Autowired
    private IPersonaServices personaServices;
    
    public PersonaServicesTest() {
        persona = new Persona();
    }
    
    @Before
    public void setUp() {
        persona = setData();
    }
    
    @Test
    public void contextLoads() {
        assertNotNull(personaServices);
    }

    /**
     * Test of get method, of class PersonaServices.
     */
    @Test
    public void testGet() {
        List<Persona> lstPersona = personaServices.get();
        assertNotNull(lstPersona);
    }

    /**
     * Test of create method, of class PersonaServices.
     */
    @Test
    public void testCreate() {
        nuevaPersona = personaServices.create(persona);
        persona.setId(nuevaPersona.getId());
        assertSame(persona, nuevaPersona);
    }

    /**
     * Test of update method, of class PersonaServices.
     */
    @Test
    public void testUpdate() {
        persona.setId(ID_ACTUALIZA_CONSULTA_TEST);
        nuevaPersona = personaServices.update(persona);
        assertEquals(persona.getId(), nuevaPersona.getId());
        assertEquals(persona.getNombres(), nuevaPersona.getNombres());
        assertEquals(persona.getApellidos(), nuevaPersona.getApellidos());
    }

    /**
     * Test of delete method, of class PersonaServices.
     */
    @Test
    public void testDelete() {
        List<Persona> lstPersonaInicial = personaServices.get();
        personaServices.delete(ID_REMOVER_TEST);
        List<Persona> lstPersonaActual = personaServices.get();
        assertNotNull(lstPersonaActual);
        assertEquals(lstPersonaInicial.size(), ID_CONSULTA_UNO_TEST);
        assertEquals(lstPersonaActual.size(), ID_CONSULTA_DOS_TEST);
    }

    /**
     * Test of getPersona method, of class PersonaServices.
     */
    @Test
    public void testGetPersona() {
        persona = personaServices.getPersona(ID_ACTUALIZA_CONSULTA_TEST);
        assertNotNull(persona);
    }
    
    
    private Persona setData() {
        Persona personaFunc = new Persona();
        
        personaFunc.setNombres(SET_DATA_NOMBRE);
        personaFunc.setApellidos(SET_DATA_APELLIDO);
        personaFunc.setCedula(SET_DATA_CEDULA);
        personaFunc.setGenero(SET_DATA_GENERO);
        personaFunc.setEdad(SET_DATA_EDAD);
        
        return personaFunc;
    }
}
