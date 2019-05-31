package com.tq.netty.learnnetty.encode;

import com.tq.netty.learnnetty.clienthandler.group.CheckGroupMembersRequestHandler;
import com.tq.netty.learnnetty.model.packets.LoginRequestPacket;
import com.tq.netty.learnnetty.model.packets.LoginResponsePacket;
import com.tq.netty.learnnetty.model.Packet;
import com.tq.netty.learnnetty.model.packets.MessageRequestPacket;
import com.tq.netty.learnnetty.model.packets.MessageResponsePacket;
import com.tq.netty.learnnetty.model.packets.grouppackets.*;
import com.tq.netty.learnnetty.serialize.JSONSerializer;
import com.tq.netty.learnnetty.serialize.Serializer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;

import java.util.HashMap;
import java.util.Map;

import static com.tq.netty.learnnetty.model.Command.*;

public class PacketCodeC {

    public static final int MAGIC_NUMBER= 0x12345678;
    private static final Map<Byte, Serializer> serializerMap;
    private static final Map<Byte, Class<? extends Packet>> packetTypeMap;

    public static final PacketCodeC INSTANCE=new PacketCodeC();
    static {
        packetTypeMap = new HashMap<>();
        packetTypeMap.put(LOGIN_REQUEST, LoginRequestPacket.class);
        packetTypeMap.put(LOGIN_RESPONSE, LoginResponsePacket.class);
        packetTypeMap.put(MESSAGE_REQUEST, MessageRequestPacket.class);
        packetTypeMap.put(MESSAGE_RESPONSE, MessageResponsePacket.class);

        packetTypeMap.put(GROUP_CREATE_REQUEST, CreateGroupRequestPacket.class);
        packetTypeMap.put(GROUP_CREATE_RESPONSE, CreateGroupResponsePacket.class);

        packetTypeMap.put(JOIN_GROUP_REQUEST, JoinGroupRequestPacket.class);
        packetTypeMap.put(JOIN_GROUP_RESPONSE, JoinGroupResponsePacket.class);

        packetTypeMap.put(LEAVE_GROUP_REQUEST, LeaveGroupRequestPacket.class);
        packetTypeMap.put(LEAVE_GROUP_RESPONSE, LeaveGroupResponsePacket.class);


        packetTypeMap.put(CHECK_GROUP_MEMBERS_REQ, CheckGroupMembersRequestPacket.class);
        packetTypeMap.put(CHECK_GROUP_MEMBERS_RES, CheckGroupMembersResponsePacket.class);


        serializerMap = new HashMap<>();
        Serializer serializer = new JSONSerializer();
        serializerMap.put(serializer.getSerializerAlgorithm(), serializer);
    }


    public ByteBuf encode(ByteBufAllocator allocator,Packet packet){
        ByteBuf byteBuf=allocator.ioBuffer();

        byte[] bytes=Serializer.DEFAULT.serialize(packet);

        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);

        return byteBuf;
    }
    public ByteBuf encode(ByteBuf byteBuf,Packet packet){

        byte[] bytes=Serializer.DEFAULT.serialize(packet);

        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);

        return byteBuf;
    }

    public Packet decode(ByteBuf byteBuf){

        byteBuf.skipBytes(4);

        byteBuf.skipBytes(1);

        byte serializeAlgorithm=byteBuf.readByte();
        byte command=byteBuf.readByte();

        int length=byteBuf.readInt();

        byte[] bytes=new byte[length];
        byteBuf.readBytes(bytes);

        Class<? extends Packet> requestType=getRequestType(command);
        Serializer serializer=getSerializer(serializeAlgorithm);

        if(requestType!=null&&serializer!=null){
            return serializer.deserialize(requestType,bytes);
        }
        return null;
    }

    private Serializer getSerializer(byte serializeAlgorithm) {

        return serializerMap.get(serializeAlgorithm);
    }

    private Class<? extends Packet> getRequestType(byte command) {

        return packetTypeMap.get(command);
    }

}
