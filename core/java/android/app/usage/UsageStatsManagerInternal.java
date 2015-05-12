/**
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy
 * of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package android.app.usage;

import android.content.ComponentName;
import android.content.res.Configuration;

/**
 * UsageStatsManager local system service interface.
 *
 * {@hide} Only for use within the system server.
 */
public abstract class UsageStatsManagerInternal {

    /**
     * Reports an event to the UsageStatsManager.
     *
     * @param component The component for which this event occurred.
     * @param userId The user id to which the component belongs to.
     * @param eventType The event that occurred. Valid values can be found at
     * {@link UsageEvents}
     */
    public abstract void reportEvent(ComponentName component, int userId, int eventType);

    /**
     * Reports an event to the UsageStatsManager.
     *
     * @param packageName The package for which this event occurred.
     * @param userId The user id to which the component belongs to.
     * @param eventType The event that occurred. Valid values can be found at
     * {@link UsageEvents}
     */
    public abstract void reportEvent(String packageName, int userId, int eventType);

    /**
     * Reports a configuration change to the UsageStatsManager.
     *
     * @param config The new device configuration.
     */
    public abstract void reportConfigurationChange(Configuration config, int userId);

    /**
     * Prepares the UsageStatsService for shutdown.
     */
    public abstract void prepareShutdown();

    /**
     * Returns true if the app has not been used for a certain amount of time. How much time?
     * Could be hours, could be days, who knows?
     *
     * @param packageName
     * @param userId
     * @return
     */
    public abstract boolean isAppIdle(String packageName, int userId);

    /**
     * Sets up a listener for changes to packages being accessed.
     * @param listener A listener within the system process.
     */
    public abstract void addAppIdleStateChangeListener(
            AppIdleStateChangeListener listener);

    /**
     * Removes a listener that was previously added for package usage state changes.
     * @param listener The listener within the system process to remove.
     */
    public abstract void removeAppIdleStateChangeListener(
            AppIdleStateChangeListener listener);

    public interface AppIdleStateChangeListener {
        void onAppIdleStateChanged(String packageName, int userId, boolean idle);
    }

}
