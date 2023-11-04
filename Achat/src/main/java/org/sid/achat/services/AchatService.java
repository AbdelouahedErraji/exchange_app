package org.sid.achat.services;

import org.sid.achat.models.AchatRequest;
import org.sid.achat.models.AchatResponse;

public interface AchatService {
    AchatResponse addAchat(AchatRequest achatRequest);
    AchatResponse getOneAchat(Long id);
//    List<AchatResponseWithProducts> getAllBooks();
}
