package subway.presentation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import subway.facade.StationFacade;
import subway.presentation.dto.StationResponse;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/stations")
public class StationController {

    private final StationFacade stationFacade;

    public StationController(final StationFacade stationFacade) {
        this.stationFacade = stationFacade;
    }

    @PostMapping
    public ResponseEntity<Void> createStation(
            @RequestParam String name
    ) {
        Long stationId = stationFacade.createStation(name);
        return ResponseEntity.created(URI.create("/stations/" + stationId)).build();
    }

    @GetMapping("/{lineId}")
    public ResponseEntity<List<StationResponse>> showStations(@PathVariable Long lineId) {
        return ResponseEntity.ok().body(stationFacade.getAllByLineId(lineId));
    }

    @PutMapping("/{stationId}")
    public ResponseEntity<Void> updateStation(
            @PathVariable Long stationId,
            @RequestParam String name
    ) {
        stationFacade.updateById(stationId, name);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{lineId}/{stationId}")
    public ResponseEntity<Void> deleteStation(
            @PathVariable Long lineId,
            @PathVariable Long stationId
    ) {
        stationFacade.deleteById(lineId, stationId);
        return ResponseEntity.noContent().build();
    }

}
