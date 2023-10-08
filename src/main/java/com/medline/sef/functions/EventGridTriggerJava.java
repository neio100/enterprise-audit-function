package com.medline.sef.functions;

import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;

/**
 * Azure Functions with Event Grid trigger.
 */
public class EventGridTriggerJava {
    /**
     * This function will be invoked when an event is received from Event Grid.
     */
    @FunctionName("EventGridTriggerJava")
    public void run(@EventGridTrigger(name = "eventGridEvent") String message,
                    @CosmosDBOutput(name = "audit", databaseName = "enterprise-audit", containerName = "enterprise-audit", createIfNotExists = true,
                            connection = "AuditDBConnection") OutputBinding<String> document,
                    final ExecutionContext context) {
        context.getLogger().info("Java Event Grid trigger function executed.");
        context.getLogger().info(message);
        document.setValue(message);
    }
}
