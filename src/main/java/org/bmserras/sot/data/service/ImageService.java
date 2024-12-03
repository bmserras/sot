package org.bmserras.sot.data.service;

import org.bmserras.sot.data.db.widget.WidgetImageDB;
import org.bmserras.sot.data.domain.WidgetImage;
import org.bmserras.sot.data.domain.Utils;
import org.bmserras.sot.data.repository.ImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.bmserras.sot.data.domain.Utils.toImage;
import static org.bmserras.sot.data.domain.Utils.toImageDB;

@Service
@Transactional
public class ImageService implements AbstractService<WidgetImage> {

    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public List<WidgetImage> findAll() {
        List<WidgetImage> widgetImages = new ArrayList<>();
        List<WidgetImageDB> imagesDB = imageRepository.findAll();
        imagesDB.forEach(widgetImageDB -> widgetImages.add(toImage(widgetImageDB)));
        return widgetImages;
    }

    @Override
    public List<WidgetImage> findAll(String filter) {
        return List.of();
    }

    @Override
    public Optional<WidgetImage> findById(String id) {
        Optional<WidgetImageDB> byId = imageRepository.findById(id);
        return byId.map(Utils::toImage);
    }

    @Override
    public Optional<WidgetImage> findByName(String name) {
        Optional<WidgetImageDB> byName = imageRepository.findByName(name);
        return byName.map(Utils::toImage);
    }

    @Override
    public void save(WidgetImage widgetImage) {
        if (widgetImage == null) return;
        WidgetImageDB widgetImageDB = toImageDB(widgetImage);
        imageRepository.save(widgetImageDB);
    }

    @Override
    public void delete(WidgetImage item) {

    }
}
