package br.com.movieflix.controller;

import br.com.movieflix.controller.request.StreamingRequest;
import br.com.movieflix.controller.response.StreamingResponse;
import br.com.movieflix.service.StreamingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieflix/streaming")
@RequiredArgsConstructor
public class StreamingController {
    private final StreamingService service;

    @GetMapping("/all")
    public ResponseEntity<List<StreamingResponse>> getAllCategorys(){
        return ResponseEntity.ok(service.findAllStreaming());
    }

    @PostMapping("/add")
    public ResponseEntity<StreamingResponse> saveCategory(@RequestBody StreamingRequest streamingRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveStreaming(streamingRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        StreamingResponse streamingById = service.findStreamingById(id);
        if (streamingById != null) {
            return ResponseEntity.ok(streamingById);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category with id " + id + " not founded");

    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        StreamingResponse streamingById = service.findStreamingById(id);
        if (streamingById != null) {
            service.deleteStreamingById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Category with id " + id + " was deleted");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category with id " + id + " not founded");

    }
}
