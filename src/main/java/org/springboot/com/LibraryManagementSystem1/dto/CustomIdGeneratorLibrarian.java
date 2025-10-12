package org.springboot.com.LibraryManagementSystem1.dto;

import java.io.Serializable;
import java.util.UUID;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class CustomIdGeneratorLibrarian implements IdentifierGenerator {
	 @Override
	    public Serializable generate(SharedSessionContractImplementor session, Object object) {
	        // Example: ALPHA + RANDOM NUMBER
	        return "EMP" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
	    }
}
