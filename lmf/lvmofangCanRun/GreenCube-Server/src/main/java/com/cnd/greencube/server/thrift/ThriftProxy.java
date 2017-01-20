package com.cnd.greencube.server.thrift;

import java.lang.reflect.Constructor;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.thrift.TMultiplexedProcessor;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;

/**
 * Thrift代理类
 * 
 * @author huxg
 * 
 */
public class ThriftProxy {
	private static final String ServicePackage = "com.cnd.greencube.server.service.";
	private static Logger logger = Logger.getLogger(ThriftProxy.class);

	private int port;// 端口

	private List<Object> services;

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public List<Object> getServices() {
		return services;
	}

	public void setServices(List<Object> services) {
		this.services = services;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void start() {
		new Thread() {
			public void run() {
				try {
					TServerSocket serverTransport = new TServerSocket(getPort());
					TMultiplexedProcessor processor = new TMultiplexedProcessor();
					String serviceName, prefix;
					for (Object service : services) {
						serviceName = service.getClass().getSimpleName();
						int idx = serviceName.indexOf("Impl");

						prefix = serviceName.substring(0, idx);

						Class serviceProcessor, serviceFace = null;
						try {
							serviceProcessor = Class.forName(ServicePackage + prefix + "$Processor");
							serviceFace = Class.forName(ServicePackage + prefix + "$Iface");

							Constructor con = serviceProcessor.getConstructor(serviceFace);
							TProcessor pr = (TProcessor) con.newInstance(service);

							processor.registerProcessor(prefix, pr);
							
						} catch (Exception e) {
							logger.error(e);
							System.exit(-2);
						}
					}

					TThreadPoolServer.Args args = new TThreadPoolServer.Args(serverTransport);
					args.protocolFactory(new TBinaryProtocol.Factory());
					args.processor(processor);
					TServer server = new TThreadPoolServer(args);
					logger.info("Thrift 服务器已启动，端口：" + getPort());
					server.serve();
				} catch (TTransportException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start();
	}
}
