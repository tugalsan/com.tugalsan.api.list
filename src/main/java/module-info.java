module com.tugalsan.api.list {
    requires com.tugalsan.api.time;
    requires com.tugalsan.api.shape;
    requires com.tugalsan.api.validator;
    requires com.tugalsan.api.pack;
    requires com.tugalsan.api.executable;
    requires com.tugalsan.api.stream;
    requires com.tugalsan.api.string;
    requires com.tugalsan.api.cast;
    exports com.tugalsan.api.list.client;
    exports com.tugalsan.api.list.server;
}
