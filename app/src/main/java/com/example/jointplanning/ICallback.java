package com.example.jointplanning;

import com.example.jointplanning.rpc.Meta;

/**
 * Универсальный обработчик функций обратного вызова
 */
public interface ICallback {
    /**
     * результат обработки обратного вызова
     * @param meta результат
     */
    void onResult(Meta meta);
}