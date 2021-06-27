package com.yuly.quasar;

public class ConstantsTest {

  public static final String GET_POSITION_REQ =
    "{\n" +
    "\"satellites\": [\n" +
    "\t{\n" +
    "\t\t\"name\": \"kenobi\",\n" +
    "\t\t\"distance\": 100.0,\n" +
    "\t\t\"message\": [\"este\", \"\", \"\", \"mensaje\", \"\"]\n" +
    "\t},\n" +
    "\t{\n" +
    "\t\t\"name\": \"skywalker\",\n" +
    "\t\t\"distance\": 115.5,\n" +
    "\t\t\"message\": [\"\", \"es\", \"\", \"\", \"secreto\"]\n" +
    "\t},\n" +
    "\t{\n" +
    "\t\t\"name\": \"sato\",\n" +
    "\t\t\"distance\": 142.7,\n" +
    "\t\t\"message\": [\"este\", \"\", \"un\", \"\", \"\"]\n" +
    "\t}\n" +
    "\t\t]\n" +
    "}";
  public static final String GET_POSITION_REQ_ERROR =
    "{\n" +
    "        \"satellites\": [\n" +
    "    {\n" +
    "        \"name\": \"kenobi\",\n" +
    "            \"distance\": 100.0,\n" +
    "            \"message\": [\"esta\", \"\", \"\", \"mensaje\", \"\"]\n" +
    "    },\n" +
    "    {\n" +
    "        \"name\": \"skywalker\",\n" +
    "            \"distance\": 115.5,\n" +
    "            \"message\": [\"\", \"es\", \"\", \"\", \"secreto\"]\n" +
    "    },\n" +
    "    {\n" +
    "        \"name\": \"sato\",\n" +
    "            \"distance\": 142.7,\n" +
    "            \"message\": [\"este\", \"\", \"un\", \"\", \"\"]\n" +
    "    }\n" +
    "\t\t]\n" +
    "}";
}
