package com.movieflix.controller;

import com.movieflix.controller.request.StreamingRequest;
import com.movieflix.controller.response.StreamingResponse;
import com.movieflix.entity.Streaming;
import com.movieflix.mapper.StreamingMapper;
import com.movieflix.service.StreamingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    
@RestController
@RequestMapping("/movieflix/streaming") 
@RequiredArgsConstructor
public class StreamingController {

        private final StreamingService streamingService;

        @GetMapping
        public ResponseEntity<List<StreamingResponse>> getAllCategories() {
            List<StreamingResponse> categories = streamingService.findAll()
                    .stream()
                    .map(StreamingMapper::toStreamingResponse)
                    .toList();
            return ResponseEntity.ok(categories);
        }

        @PostMapping
        public ResponseEntity<StreamingResponse> saveStreaming(@RequestBody StreamingRequest request) {
            Streaming newStreaming = StreamingMapper.toStreaming(request);
            Streaming savedStreaming = streamingService.saveStreaming(newStreaming);
            return ResponseEntity.status(HttpStatus.CREATED).body(StreamingMapper.toStreamingResponse(savedStreaming));
        }

        @GetMapping("/{id}")
        public ResponseEntity<StreamingResponse> getByStreamingId(@PathVariable Long id) {
            return streamingService.findById(id)
                    .map(streaming -> ResponseEntity.ok(StreamingMapper.toStreamingResponse(streaming)))
                    .orElse(ResponseEntity.notFound().build());
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteByStreamingId(@PathVariable Long id) {
            streamingService.deleteStreaming(id);
            return ResponseEntity.noContent().build();
        }
    }
