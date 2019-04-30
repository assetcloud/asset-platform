/**
  * Copyright 2019 bejson.com 
  */
package com.asset.javabean;
import java.util.List;

/**
 * Auto-generated: 2019-04-09 18:0:10
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class JsonRootBean {

    private String resourceId;
    private Properties properties;
    private Stencil stencil;
    private List<String> childShapes;
    private List<Outgoing> outgoing;
    private Bounds bounds;
    private List<String> dockers;
    public void setResourceId(String resourceId) {
         this.resourceId = resourceId;
     }
     public String getResourceId() {
         return resourceId;
     }

    public void setProperties(Properties properties) {
         this.properties = properties;
     }
     public Properties getProperties() {
         return properties;
     }

    public void setStencil(Stencil stencil) {
         this.stencil = stencil;
     }
     public Stencil getStencil() {
         return stencil;
     }

    public void setChildShapes(List<String> childShapes) {
         this.childShapes = childShapes;
     }
     public List<String> getChildShapes() {
         return childShapes;
     }

    public void setOutgoing(List<Outgoing> outgoing) {
         this.outgoing = outgoing;
     }
     public List<Outgoing> getOutgoing() {
         return outgoing;
     }

    public void setBounds(Bounds bounds) {
         this.bounds = bounds;
     }
     public Bounds getBounds() {
         return bounds;
     }

    public void setDockers(List<String> dockers) {
         this.dockers = dockers;
     }
     public List<String> getDockers() {
         return dockers;
     }

}