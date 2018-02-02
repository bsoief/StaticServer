package com.luyichao.staticserver.server;

import com.luyichao.staticserver.config.Config;
import com.luyichao.staticserver.server.handler.ProcessHandler;
import com.luyichao.staticserver.util.TypeConverter;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;

public class StaticServer {

    public static StaticServer getInstance() {
        return InternalClass.getInstance();
    }

    private static class InternalClass {
        private static StaticServer staticServer = new StaticServer();

        public static StaticServer getInstance() {
            return staticServer;
        }
    }

    private Config config;

    private Integer port;

    private ServerBootstrap bootstrap;

    private EventLoopGroup bossGroup;

    private EventLoopGroup workerGroup;

    private StaticServer() {
        config = Config.getInstance();
        bootstrap = new ServerBootstrap();

        Integer bossThreadNumber = TypeConverter.stringToInteger(config.getBossGroupEventLoopNumber());
        if (bossThreadNumber == null) {
            bossThreadNumber = new Integer(1);
        }
        bossGroup = new NioEventLoopGroup(bossThreadNumber);

        Integer workerThreadNumber = TypeConverter.stringToInteger(config.getWorkerGroupEventLoopNumber());
        if (workerThreadNumber == null) {
            workerGroup = new NioEventLoopGroup();
        } else {
            workerGroup = new NioEventLoopGroup(workerThreadNumber);
        }

        Integer portNumber = TypeConverter.stringToInteger(config.getPort());
        if (portNumber != null) {
            port = portNumber;
        } else {
            port = 8080;
        }
    }

    public void start() {

        bootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        pipeline.addLast(new HttpServerCodec());
                        pipeline.addLast(new HttpObjectAggregator(65536));
                        pipeline.addLast(new ChunkedWriteHandler());
                        pipeline.addLast(new ProcessHandler());
                    }
                });

        try {
            ChannelFuture future = bootstrap.bind(port).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            System.err.println(e);
        } catch (Throwable t) {
            System.err.println(t);
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
