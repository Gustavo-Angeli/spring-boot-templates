package com.gusta.template.service;

import com.gusta.template.mocks.*;
import com.gusta.template.model.entities.*;
import com.gusta.template.model.vo.*;
import com.gusta.template.repository.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    private final MockPerson input = new MockPerson();
    private PersonEntity entity = input.mockEntity();
    private final PersonEntity persisted = entity;
    private PersonVO vo = input.mockVO();

    @InjectMocks
    private PersonService service;
    @Mock
    private PersonRepository repository;

    @BeforeAll
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @AfterEach
    void reset() {
        entity = input.mockEntity();
        vo = input.mockVO();
    }

    @Test
    void testCreatePerson_NullOrBlankParam(){
        vo.setName(null);
        assertThrows(IllegalArgumentException.class, () -> service.createPerson(vo));
        vo.setName("");
        assertThrows(IllegalArgumentException.class, () -> service.createPerson(vo));
    }
    @Test
    void testCreatePerson_Success() {
        assertEquals(vo, service.createPerson(vo));
    }

    @Test
    void testFindById_NullOrBlankParam() {
        assertThrows(IllegalArgumentException.class, () -> service.findPersonById(null));
        assertThrows(IllegalArgumentException.class, () -> service.findPersonById(Long.valueOf("")));
    }
    @Test
    void testFindById_PersonNotFound() {
        when(repository.findById(vo.getId())).thenReturn(null);

        assertThrows(NullPointerException.class, () -> service.findPersonById(vo.getId()));
    }
    @Test
    void testFindById_Success() {
        when(repository.findById(vo.getId())).thenReturn(Optional.ofNullable(entity));

        assertEquals(vo, service.findPersonById(vo.getId()));
    }

    @Test
    void testFindByName_NullOrBlankParam() {
        assertThrows(IllegalArgumentException.class, () -> service.findByName(null));
        assertThrows(IllegalArgumentException.class, () -> service.findByName(""));
    }
    @Test
    void testFindByName_PersonNotFound() {
        when(repository.findByName(vo.getName())).thenReturn(null);

        assertThrows(NullPointerException.class, () -> service.findByName(vo.getName()));
    }
    @Test
    void testFindByName_Success() {
        when(repository.findByName(vo.getName())).thenReturn(Optional.ofNullable(entity));

        assertEquals(vo, service.findByName(vo.getName()));
    }

    @Test
    void testFindAll_Success() {
        when(repository.findAll()).thenReturn(Arrays.asList(entity));

        assertEquals(Arrays.asList(vo), service.findAll());
    }

    @Test
    void testUpdatePersonById_NullOrBlankParams() {
        vo.setName(null);

        assertThrows(IllegalArgumentException.class, () -> service.updatePersonById(vo));

        vo.setName("");

        assertThrows(IllegalArgumentException.class, () -> service.updatePersonById(vo));
    }
    @Test
    void testUpdatePersonById_PersonNotFound() {
        when(repository.findById(0L)).thenReturn(null);

        assertThrows(NullPointerException.class, () -> service.updatePersonById(vo));
    }
    @Test
    void testUpdatePersonById_Success() {
        when(repository.findById(0L)).thenReturn(Optional.ofNullable(entity));

        assertEquals(vo, service.updatePersonById(vo));
    }

    @Test
    void testDeactivatePersonById_NullOrBlankParams() {
        assertThrows(IllegalArgumentException.class, () -> service.deactivatePersonById(null));
        assertThrows(IllegalArgumentException.class, () -> service.deactivatePersonById(Long.valueOf("")));
    }
    @Test
    void testDeactivatePersonById_PersonNotFound() {
        when(repository.findById(0L)).thenReturn(null);

        assertThrows(NullPointerException.class, () -> service.deactivatePersonById(0L));
    }
    @Test
    void testDeactivatePersonById_Success() {
        vo.setActivated(false);

        when(repository.findById(0L)).thenReturn(Optional.ofNullable(entity));

        assertEquals(vo, service.deactivatePersonById(0L));
    }

    @Test
    void testActivePersonById_NullOrBlankParams() {
        assertThrows(IllegalArgumentException.class, () -> service.deactivatePersonById(null));
        assertThrows(IllegalArgumentException.class, () -> service.deactivatePersonById(Long.valueOf("")));
    }
    @Test
    void testActivePersonById_PersonNotFound() {
        when(repository.findById(0L)).thenReturn(null);

        assertThrows(NullPointerException.class, () -> service.deactivatePersonById(0L));
    }
    @Test
    void testActivePersonById_Success() {
        when(repository.findById(0L)).thenReturn(Optional.ofNullable(entity));

        assertEquals(vo, service.activePersonById(0L));
    }

    @Test
    void testDeletePersonById_NullOrBlankParams() {
        assertThrows(IllegalArgumentException.class, () -> service.deletePersonById(null));
        assertThrows(IllegalArgumentException.class, () -> service.deletePersonById(Long.valueOf("")));
    }
    @Test
    void testDeletePersonById_PersonNotFound() {
        when(repository.findById(0L)).thenReturn(null);

        assertThrows(NullPointerException.class, () -> service.deletePersonById(0L));
    }
    @Test
    void testDeletePersonById_Success() {
        service.deletePersonById(0L);
    }

}
