package com.espark.adarsh.bean;

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.DataSerializable;
import lombok.Data;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

@Data
public class Person implements DataSerializable {

    Long id;
    String name;
    String email;

    @Override
    public void readData(ObjectDataInput in)
            throws IOException {
        this.id = in.readLong();
        this.email = in.readUTF();
        this.name = in.readUTF();
    }

    @Override
    public void writeData(ObjectDataOutput out)
            throws IOException {
        out.writeLong(id);
        out.writeUTF(email);
        out.writeUTF(name);
    }
}
