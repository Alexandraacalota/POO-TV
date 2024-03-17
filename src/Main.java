import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import input.Input;
import pootv.PooTv;

import java.io.File;
import java.io.IOException;

public class Main {
    /** Main method that reads from json file, calls the start method of the PooTv class
     * and writes the output in another json file */
    public static void main(final String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Input inputData = objectMapper.readValue(new File(args[0]),
                Input.class);

        ArrayNode output = objectMapper.createArrayNode();

        PooTv pooTv = new PooTv();
        pooTv.start(inputData, objectMapper, output);

        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(new File(args[1]), output);
    }
}
