package com.landon.converter;

import com.landon.entity.RoomEntity;
import com.landon.model.Links;
import com.landon.model.Self;
import com.landon.model.response.ReservationResponse;
import com.landon.rest.ResourceConstants;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RoomEntityToReservationResponseConverter implements Converter<RoomEntity, ReservationResponse> {
    @Override
    public ReservationResponse convert(RoomEntity source) {
        ReservationResponse res = new ReservationResponse();
        res.setRoomNumber( source.getRoomNumber() );
        res.setPrice( Integer.valueOf(source.getPrice()) );

        Self self = new Self();
        self.setRef(ResourceConstants.ROOM_RESERVATION_V1 + "/" + source.getId());

        Links links = new Links();
        links.setSelf(self);

        res.setLinks(links);

        return res;
    }
}
