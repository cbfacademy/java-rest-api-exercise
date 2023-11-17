package com.cbfacademy.restapiexercise.ious;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class ListIOUServiceTest {

    private ListIOUService service;
    private IOU iou1, iou2;

    @BeforeEach
    void setUp() {
        service = new ListIOUService();
        iou1 = new IOU("Borrower1", "Lender1", BigDecimal.valueOf(1000), Instant.now());
        iou2 = new IOU("Borrower2", "Lender2", BigDecimal.valueOf(500), Instant.now());
    }

    @Test
    void testGetAllIOUsInitiallyEmpty() {
        assertTrue(service.getAllIOUs().isEmpty());
    }

    @Test
    void testGetAllIOUsAfterAdding() {
        service.createIOU(iou1);
        service.createIOU(iou2);
        List<IOU> ious = service.getAllIOUs();

        assertEquals(2, ious.size());
        assertTrue(ious.contains(iou1));
        assertTrue(ious.contains(iou2));
    }

    @Test
    void testGetIOUExisting() {
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
        IOU created = service.createIOU(iou1);

        assertNotNull(created.getId());
        assertEquals(iou1.getBorrower(), created.getBorrower());
        assertEquals(iou1.getLender(), created.getLender());
        assertEquals(iou1.getAmount(), created.getAmount());
    }

    @Test
    void testUpdateIOUExisting() {
        service.createIOU(iou1);
        IOU updatedIOU = new IOU("UpdatedBorrower", "UpdatedLender", BigDecimal.valueOf(1500), Instant.now());
        updatedIOU.setBorrower("UpdatedBorrower");
        IOU updated = service.updateIOU(iou1.getId(), updatedIOU);

        assertNotNull(updated);
        assertEquals("UpdatedBorrower", updated.getBorrower());
    }

    @Test
    void testUpdateIOUNonExisting() {
        assertNull(service.updateIOU(UUID.randomUUID(),
                new IOU("Borrower3", "Lender3", BigDecimal.valueOf(200), Instant.now())));
    }

    @Test
    void testDeleteIOUExisting() {
        service.createIOU(iou1);
        boolean deleted = service.deleteIOU(iou1.getId());

        assertTrue(deleted);
        assertFalse(service.getAllIOUs().contains(iou1));
    }

    @Test
    void testDeleteIOUNonExisting() {
        assertFalse(service.deleteIOU(UUID.randomUUID()));
    }
}
