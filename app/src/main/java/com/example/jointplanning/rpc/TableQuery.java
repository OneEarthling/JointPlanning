package com.example.jointplanning.rpc;

public class TableQuery {
    /**
     * имя таблицы для обработки
     */
    public String action;

    /**
     * псевдоним action
     */
    public String alias;

    /**
     * список полей которые требуется получить от сервера
     */
    public String select = "";

    /**
     *
     * @param action
     * @param alias псевдоним
     * @param select
     */
    public TableQuery(String action, String alias, String select) {
        this.action = action;
        this.alias = alias;
        this.select = select;
    }

    /**
     * Создание объекта
     * @param action таблицы
     * @param select выборка полей
     * @return объект TableQuery
     */
    public TableQuery (String action, String select) {
        this(action, null, select);
    }

    /**
     * Создание объекта
     * @param action таблицы
     * @return объект TableQuery
     */
    public TableQuery (String action) {
        this(action, null, "");
    }

    /**
     * Преобразование в RPC запрос
     * @param limit лимит
     * @param filters фильтрация
     * @return RPC запрос
     */
    public RPCItem toRPCQuery(int limit, Object[] filters){
        QueryData query = new QueryData();
        query.limit = limit;
        query.alias = alias;
        query.select = select;
        if(filters != null) {
            query.filter = filters;
        }

        RPCItem item = new RPCItem();
        item.action = action;
        item.method = "Query";
        item.data = new Object[1];
        item.data[0] = query;
        return item;
    }

    /**
     * Преобразование в RPC запрос
     * @param obj дополнительные параметры в функцию
     * @return RPC запрос
     */
    public RPCItem toRPCSelect(Object obj){
        RPCItem item = new RPCItem();
        item.action = action;
        item.method = "Select";
        item.data = new Object[1];
        item.data[0] = new SingleItemQuery(obj);
        return item;
    }
}