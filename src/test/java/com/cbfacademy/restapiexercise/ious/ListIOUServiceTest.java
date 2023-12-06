package com.cbfacademy.restapiexercise.ious;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class ListIOUServiceTest {

    private ListIOUService service;
    private IOURepository mockRepository;
    private IOU iou1, iou2;

    @BeforeEach
    void setUp() {
        mockRepository = Mockito.mock(IOURepository.class);
        service = new ListIOUService(mockRepository);
        iou1 = new IOU("Borrower1", "Lender1", BigDecimal.valueOf(1000), Instant.now());
        iou2 = new IOU("Borrower2", "Lender2", BigDecimal.valueOf(500), Instant.now());
    }

    @Test
    void testGetAllIOUsInitiallyEmpty() {
        Mockito.when(mockRepository.retrieveAll()).thenReturn(List.of());
        assertTrue(service.getAllIOUs().isEmpty());
    }

    @Test
    void testGetAllIOUsAfterAdding() {
        Mockito.when(mockRepository.create(Mockito.any(IOU.class))).thenAnswer(invocation -> invocation.getArgument(0));
        Mockito.when(mockRepository.retrieveAll()).thenReturn(List.of(iou1, iou2));
        service.createIOU(iou1);
        service.createIOU(iou2);
        List<IOU> ious = service.getAllIOUs();

        assertEquals(2, ious.size());
        assertTrue(ious.contains(iou1));
        assertTrue(ious.contains(iou2));
    }

    @Test
    void testGetIOUExisting() {
        Mockito.when(mockRepository.create(Mockito.any(IOU.class))).thenAnswer(invocation -> invocation.getArgument(0));
        Mockito.when(mockRepository.retrieve(iou1.getId())).thenReturn(iou1);
        service.createIOU(iou1);
        IOU found = service.getIOU(iou1.getId());

        assertEquals(iou1.getId(), found.getId());
    }

    @Test
    void testGetIOUNonExisting() {
        assertNull(service.getIOU(UUID.randomUUID()));
    }

    @Test
    void testCreateIOU() {
        Mockito.when(mockRepository.create(Mockito.any(IOU.class))).thenAnswer(invocation -> invocation.getArgument(0));
        IOU created = service.createIOU(iou1);

        assertNotNull(created.getId());
        assertEquals(iou1.getBorrower(), created.getBorrower());
        assertEquals(iou1.getLender(), created.getLender());
        assertEquals(iou1.getAmount(), created.getAmount());
    }

    @Test
    void testUpdateIOUExisting() {
        Mockito.when(mockRepository.create(Mockito.any(IOU.class))).thenAnswer(invocation -> invocation.getArgument(0));
        Mockito.when(mockRepository.update(Mockito.any(IOU.class))).thenAnswer(invocation -> invocation.getArgument(0));
        service.createIOU(iou1);
        IOU updatedIOU = new IOU("UpdatedBorrower", "UpdatedLender", BigDecimal.valueOf(1500), Instant.now());
        updatedIOU.setBorrower("UpdatedBorrower");
        IOU updated = service.updateIOU(iou1.getId(), updatedIOU);

        assertNotNull(updated);
        assertEquals("UpdatedBorrower", updated.getBorrower());
    }

    @Test
    void testUpdateIOUNonExisting() {
        Mockito.when(mockRepository.update(Mockito.any(IOU.class))).thenReturn(null);
        assertNull(service.updateIOU(UUID.randomUUID(),
                new IOU("Borrower3", "Lender3", BigDecimal.valueOf(200), Instant.now())));
    }

    @Test
    void testDeleteIOUExisting() {
        Mockito.when(mockRepository.delete(Mockito.any(UUID.class))).thenReturn(true);
        service.createIOU(iou1);
        boolean deleted = service.deleteIOU(iou1.getId());

        assertTrue(deleted);
        assertFalse(service.getAllIOUs().contains(iou1));
    }

    @Test
    void testDeleteIOUNonExisting() {
        Mockito.when(mockRepository.delete(Mockito.any(UUID.class))).thenReturn(false);
        assertFalse(service.deleteIOU(UUID.randomUUID()));
    }
}
