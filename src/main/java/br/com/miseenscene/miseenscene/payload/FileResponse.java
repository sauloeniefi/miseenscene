package br.com.miseenscene.miseenscene.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class FileResponse {

    String fileName;
    String message;
}
