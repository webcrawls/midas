package live.webcrawls.midas.common.module;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModuleRegistry {

    private final Map<String, MidasModule> modules;

    public ModuleRegistry() {
        this.modules = new HashMap<>();
    }

    public void registerModule(MidasModule module) {
        this.modules.put(module.id(), module);
    }

    public void unregisterModule(MidasModule module) {
        this.modules.remove(module.id(), module);
    }

    public List<MidasModule> all() {
        return List.copyOf(this.modules.values());
    }

}
