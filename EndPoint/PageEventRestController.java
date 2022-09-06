package com.example.springcloudprocesssing.EndPoint;
import com.example.springcloudprocesssing.Model.PageEvent;
import lombok.AllArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Random;

@RestController
@AllArgsConstructor
public class PageEventRestController {

    private final StreamBridge streamBridge;
    @GetMapping("/publish/{topic}/{name}")
    public PageEvent publish(@PathVariable String topic,
                             @PathVariable String name){

        PageEvent pageEvent=new

                PageEvent(name,Math.random()>0.5?"U1":"U2",new Date(),new
                Random().nextInt(9000));

        streamBridge.send(topic,pageEvent);
        return pageEvent;
    }




   /* private final  InteractiveQueryService interactiveQueryService;


    @GetMapping(path = "/analytics",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Map<String, Long>> analytics(){
        return Flux.interval(Duration.ofSeconds(1))

                .map(sequence->{
                    Map<String,Long> stringLongMap=new HashMap<>();
                    ReadOnlyWindowStore<String, Long> stats =
                            interactiveQueryService.getQueryableStore("stats-store", QueryableStoreTypes.windowStore());
                    Instant now=Instant.now();
                    Instant from=now.minusMillis(5000);
                    KeyValueIterator<Windowed<String>, Long> fetchAll = stats.fetchAll(from, now);
//WindowStoreIterator<Long> fetch = stats.fetch(name, from, now);
                    while (fetchAll.hasNext()){
                        KeyValue<Windowed<String>, Long> next = fetchAll.next();
                        stringLongMap.put(next.key.key(),next.value);
                    }
                    return stringLongMap;
                }).share();

    }*/
}
