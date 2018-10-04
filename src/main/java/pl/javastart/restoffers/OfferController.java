package pl.javastart.restoffers;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class OfferController {
    private OfferRepository offerRepository;

    public OfferController(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

//    @GetMapping("/api/offers")
//    public List<Offer> getList(@RequestParam(required = false)
//                                       String title) {
//        if (title != null) {
//            return offerRepository.findByTitleIgnoreCaseContains(title);
//        }
//        return offerRepository.findAll();
//    }

    @GetMapping("/api/offers")
    public List<OfferDto> getAll() {
        List<Offer> all = offerRepository.findAll();

        List<OfferDto> offerDtos = new ArrayList<>();

        for (Offer offer : all) {//zamieniamy oferty na tym OfferDto, gdzie jest tylko nazwa kategorii
            ModelMapper modelMapper = new ModelMapper();
            OfferDto dto = modelMapper.map(offer, OfferDto.class);
            dto.setCategory(offer.getCategory().getName());
            offerDtos.add(dto);
        }

        return offerDtos;
    }

    @PostMapping("/api/offers")
    public Offer insert(@RequestBody Offer offer) {
        offerRepository.save(offer);
        return offer;
    }

    @GetMapping("/api/offers/{id}")
    public Offer getOne(@PathVariable Long id) {
        //Optional<Offer> opt = offerRepository.findById(id);
        return offerRepository.findById(id).orElse(null);
    }

    @GetMapping("/api/offers/count")
    public long offerCount() {
        long offerNumber = offerRepository.count();
        return offerNumber;
    }

    @DeleteMapping("/api/offers/{id}")
    public void delete(@PathVariable Long id) {
        offerRepository.deleteById(id);
    }


}
