package cn.org.assetcloud.system.user.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(
        description = "查询条件"
)
public class Query {

    @ApiModelProperty("当前页")
    private Integer page;

    @ApiModelProperty("每页的数量")
    private Integer size;

    @ApiModelProperty(
            hidden = true
    )
    private String ascs;

    @ApiModelProperty(
            hidden = true
    )
    private String descs;

    public Query() {
    }

    public Integer getPage() {
        return this.page;
    }

    public Integer getSize() {
        return this.size;
    }

    public String getAscs() {
        return this.ascs;
    }

    public String getDescs() {
        return this.descs;
    }

    public Query setPage(final Integer page) {
        this.page = page;
        return this;
    }

    public Query setSize(final Integer size) {
        this.size = size;
        return this;
    }

    public Query setAscs(final String ascs) {
        this.ascs = ascs;
        return this;
    }

    public Query setDescs(final String descs) {
        this.descs = descs;
        return this;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Query)) {
            return false;
        } else {
            Query other = (Query)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label59: {
                    Object this$page = this.getPage();
                    Object other$page = other.getPage();
                    if (this$page == null) {
                        if (other$page == null) {
                            break label59;
                        }
                    } else if (this$page.equals(other$page)) {
                        break label59;
                    }

                    return false;
                }

                Object this$size = this.getSize();
                Object other$size = other.getSize();
                if (this$size == null) {
                    if (other$size != null) {
                        return false;
                    }
                } else if (!this$size.equals(other$size)) {
                    return false;
                }

                Object this$ascs = this.getAscs();
                Object other$ascs = other.getAscs();
                if (this$ascs == null) {
                    if (other$ascs != null) {
                        return false;
                    }
                } else if (!this$ascs.equals(other$ascs)) {
                    return false;
                }

                Object this$descs = this.getDescs();
                Object other$descs = other.getDescs();
                if (this$descs == null) {
                    if (other$descs != null) {
                        return false;
                    }
                } else if (!this$descs.equals(other$descs)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Query;
    }

    public int hashCode() {
        boolean PRIME = true;
        int result = 1;
        Object $page = this.getPage();
        result = result * 59 + ($page == null ? 43 : $page.hashCode());
        Object $size = this.getSize();
        result = result * 59 + ($size == null ? 43 : $size.hashCode());
        Object $ascs = this.getAscs();
        result = result * 59 + ($ascs == null ? 43 : $ascs.hashCode());
        Object $descs = this.getDescs();
        result = result * 59 + ($descs == null ? 43 : $descs.hashCode());
        return result;
    }

    public String toString() {
        return "Query(page=" + this.getPage() + ", size=" + this.getSize() + ", ascs=" + this.getAscs() + ", descs=" + this.getDescs() + ")";
    }
}

