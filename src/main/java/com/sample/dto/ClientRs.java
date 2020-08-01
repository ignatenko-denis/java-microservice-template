package com.sample.dto;

import com.sample.entity.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientRs {
    private List<Client> items = new ArrayList<>();
}
