package yardspoon.requestspy;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class SpiedRequest {
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Long key;
    
    @Persistent
    private String method;
    
    @Persistent
    private String path;
    
    @Persistent
    private String caller;
    
    @Persistent
    private Date when;

	public Long getKey() {
		return key;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getCaller() {
		return caller;
	}

	public void setCaller(String caller) {
		this.caller = caller;
	}

	public Date getWhen() {
		return when;
	}

	public void setWhen(Date when) {
		this.when = when;
	}
	
	@Override
	public String toString() {
		return "SpiedRequest[" + getMethod() + "|" + getPath() + "|" + getCaller() + "|" + getWhen() + "]";
	}
    
}
