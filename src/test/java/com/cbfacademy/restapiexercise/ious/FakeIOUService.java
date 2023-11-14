package com.cbfacademy.restapiexercise.ious;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Fake service class to manage IOU objects in unit tests.
 */
@Primary
@Service
public class FakeIOUService implements IOUService {

    private final List<IOU> ious = new ArrayList<>();

    @Override
    public List<IOU> getAllIOUs() {
        return ious;
    }

    @Override
    public IOU getIOU(UUID id) {
        return ious.stream()
                .filter(iou -> iou.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public IOU createIOU(IOU iou) {
        ious.add(iou);

        return iou;
    }

    @Override
    public IOU updateIOU(UUID id, IOU updatedIOU) {
        for (int i = 0; i < ious.size(); i++) {
            IOU iou = ious.get(i);

            if (iou.getId().equals(id)) {
                ious.set(i, updatedIOU);

                return updatedIOU;
            }
        }

        return null;
    }

    @Override
    public boolean deleteIOU(UUID id) {
        return ious.removeIf(iou -> iou.getId().equals(id));
    }
}
