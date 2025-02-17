package br.com.movieflix.service;

import br.com.movieflix.controller.request.StreamingRequest;
import br.com.movieflix.controller.response.StreamingResponse;
import br.com.movieflix.entity.Streaming;
import br.com.movieflix.mapper.StreamingMapper;
import br.com.movieflix.repository.StreamingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StreamingService {
    private final StreamingRepository repository;


    public List<StreamingResponse> findAllStreaming(){
        List<Streaming> categoryList = repository.findAll();
        return categoryList.stream().map(StreamingMapper::toStreamingResponse).collect(Collectors.toList());
    }

    public StreamingResponse saveStreaming(StreamingRequest streamingRequest) {
        Streaming streaming = StreamingMapper.toStreaming(streamingRequest);
        Streaming streamingSave = repository.save(streaming);
        return StreamingMapper.toStreamingResponse(streamingSave);
    }

    public StreamingResponse findStreamingById(Long id){
        Optional<Streaming> streamingOptional = repository.findById(id);
        return streamingOptional.map(StreamingMapper::toStreamingResponse).orElse(null);

    }

    public void deleteStreamingById(Long id) {
        repository.deleteById(id);
    }
}
