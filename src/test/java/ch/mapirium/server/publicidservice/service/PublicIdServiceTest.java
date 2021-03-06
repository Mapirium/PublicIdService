package ch.mapirium.server.publicidservice.service;

import ch.mapirium.server.publicidservice.exception.UniqueKeyGenerationFailedException;
import ch.mapirium.server.publicidservice.model.PublicIdEntity;
import ch.mapirium.server.publicidservice.repo.PublicIdRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.dao.DataIntegrityViolationException;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

/**
 * Unit-Test für die Klasse {@link PublicIdService}
 */
@RunWith(MockitoJUnitRunner.class)
public class PublicIdServiceTest {

    @Mock
    private PublicIdRepository publicIdRepository;

    @Spy
    @InjectMocks
    private PublicIdService sut;

    @Test
    public void testGeneratePublicKey() throws Exception {
        // Mit Prefix erstellen
        String result = sut.generatePublicKey("UT", 6);
        Assert.assertEquals(8, result.length());
        Assert.assertTrue(result.startsWith("UT"));

        // Ohne Prefix erstellen
        result = sut.generatePublicKey("", 6);
        Assert.assertEquals(6, result.length());

        // Sicherstellen, das jeweils ein anderes Resultat kommt
        String result2 = sut.generatePublicKey("", 6);
        Assert.assertNotEquals(result, result2);
    }

    @Test
    public void testCreateNewPublicIdWithKey() throws Exception {
        // Mock erstellen
        PublicIdEntity savedEntity = mock(PublicIdEntity.class);
        Mockito.when(this.publicIdRepository.save(Mockito.any(PublicIdEntity.class))).thenReturn(savedEntity);

        // Test ausführen
        PublicIdEntity result = sut.createNewPublicId("UT1234");

        // Mock überprüfen
        Assert.assertEquals(savedEntity, result);

        ArgumentCaptor<PublicIdEntity> entityCaptor = ArgumentCaptor.forClass(PublicIdEntity.class);
        Mockito.verify(publicIdRepository).save(entityCaptor.capture());
        Assert.assertEquals("UT1234", entityCaptor.getValue().getPublicId());
    }

    @Test
    public void testCreateNewPublicIdWithKeyDuplicate() throws Exception {
        // Mock erstellen
        Mockito.when(this.publicIdRepository.save(Mockito.any(PublicIdEntity.class))).thenThrow(DataIntegrityViolationException.class);

        // Test ausführen
        PublicIdEntity result = sut.createNewPublicId("UT1234");

        // Mock überprüfen
        Assert.assertNull(result);
    }

    @Test
    public void testCreateNewPublicId() throws Exception {
        // Mock aufsetzen
        PublicIdEntity entity = mock(PublicIdEntity.class);
        doReturn("UT1234").when(sut).generatePublicKey("UT", 4);
        doReturn(entity).when(sut).createNewPublicId("UT1234");

        // Test ausführen
        PublicIdEntity result = sut.createNewPublicId("UT", 4);

        // Überprüfen
        Assert.assertEquals(entity, result);
    }

    @Test(expected = UniqueKeyGenerationFailedException.class)
    public void testCreateNewPublicIdDuplicate() throws Exception {
        // Mock aufsetzen
        PublicIdEntity entity = mock(PublicIdEntity.class);
        doReturn("UT1234").when(sut).generatePublicKey("UT", 4);
        doReturn(null).when(sut).createNewPublicId("UT1234");

        // Test ausführen
        PublicIdEntity result = sut.createNewPublicId("UT", 4);
    }
}