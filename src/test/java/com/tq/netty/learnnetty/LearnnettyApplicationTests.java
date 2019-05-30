package com.tq.netty.learnnetty;

import com.tq.netty.learnnetty.clienthandler.TestInBoundHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LearnnettyApplicationTests {

	@Test
	public void contextLoads() {

		NioEventLoopGroup worker=new NioEventLoopGroup();
		NioEventLoopGroup boss=new NioEventLoopGroup();

		ServerBootstrap bootstrap=new ServerBootstrap();

		bootstrap.childHandler(new ChannelInitializer<NioSocketChannel>() {

			@Override
			protected void initChannel(NioSocketChannel ch) throws Exception {
				ch.pipeline().addLast(new TestInBoundHandler.InBoundHandlerA());
				ch.pipeline().addLast(new TestInBoundHandler.InBoundHandlerB());
				ch.pipeline().addLast(new TestInBoundHandler.InBoundHandlerC());

			}
		}).bind(8081);

		/*Serializer serializer = new JSONSerializer();
		LoginRequestPacket loginRequestPacket = new LoginRequestPacket();

		loginRequestPacket.setVersion(((byte) 1));
		loginRequestPacket.setUserId(123);
		loginRequestPacket.setUsername("zhangsan");
		loginRequestPacket.setPassword("password");

		PacketCodeC packetCodeC = new PacketCodeC();
		ByteBuf byteBuf = packetCodeC.encode(loginRequestPacket);
		Packet decodedPacket = packetCodeC.decode(byteBuf);*/

		//Assert.assertArrayEquals(serializer.serialize(//loginRequestPacket), serializer.serialize(decodedPacket));
		/*ByteBuf buf= ByteBufAllocator.DEFAULT.buffer(9,100);
		print("allocate bytebuf init 9,max 100",buf);

		buf.writeBytes(new byte[]{1,2,3,4});
		print("after write 1,2,3,4---",buf);

		buf.writeInt(99);
		print("after write 99-----",buf);

		buf.writeBytes(new byte[]{5});
		print("writeBytes[5]--",buf);

		buf.writeBytes(new byte[]{6});
		print("write bytes{6}-----",buf);

		System.out.println("getBytes(2)----return -"+buf.getByte(2));
		System.out.println("getShort(3)---return---"+buf.getShort(3));
		System.out.println("getInt(3) --return---"+buf.getInt(3));
		print("getByte()---",buf);

		buf.setByte(buf.readableBytes()+1,0);
		print("setByte()",buf);

		byte[] dst=new byte[buf.readableBytes()];
		buf.readBytes(dst);
		print("readBytes("+dst.length+")",buf);*/
	}

	private  static  void print(String action, ByteBuf buffer){
		System.out.println("after -------"+action+"---------");
		System.out.println("capacity: "+buffer.capacity());
		System.out.println("maxCapacity: "+buffer.maxCapacity());
		System.out.println("readerIndex: "+buffer.readerIndex());
		System.out.println("readableBytes: "+buffer.readableBytes());
		System.out.println("isReadable: "+buffer.isReadable());
		System.out.println("writerIndex: "+buffer.writerIndex());
		System.out.println("writableBytes: "+buffer.writableBytes());
		System.out.println("isWritable: "+buffer.isWritable());
		System.out.println("maxWritableBytes: "+buffer.maxWritableBytes());
		System.out.println();
	}
}
