package com.zr.aservice.rbac.config;

import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.Server;

import java.util.List;

/**
 * 自定义负载均衡策略
 */
public class MyLoadBanlanceRule implements IRule {
  private ILoadBalancer lb;

  @Override
  public Server choose(Object key) {
    List<Server> servers = lb.getAllServers();
    for (Server server : servers) {
      System.out.println(server.getHostPort());
    }
    //这里没写算法，只是将服务列表中的第一个Server返回
    return servers.get(0);
  }

  @Override
  public void setLoadBalancer(ILoadBalancer lb) {
    this.lb = lb;
  }

  @Override
  public ILoadBalancer getLoadBalancer() {
    return lb;
  }
}
