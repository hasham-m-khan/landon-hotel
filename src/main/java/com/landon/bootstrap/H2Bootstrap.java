package com.landon.bootstrap;

import com.landon.entity.RoomEntity;
import com.landon.repository.RoomRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class H2Bootstrap implements CommandLineRunner {

    RoomRepository roomRepository;

    public H2Bootstrap(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        roomRepository.save(new RoomEntity(405, "200"));
        roomRepository.save(new RoomEntity(406, "220"));
        roomRepository.save(new RoomEntity(407, "250"));
        roomRepository.save(new RoomEntity(408, "270"));
        roomRepository.save(new RoomEntity(409, "280"));

        List<RoomEntity> rooms = roomRepository.findAll();

        System.out.println("Printing Rooms data: ");
        for (RoomEntity room : rooms) {
            System.out.println("--> Room id: " + room.getRoomNumber());
        }
    }
}
