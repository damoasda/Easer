/*
 * Copyright (c) 2016 - 2019 Rui Zhao <renyuneyun@gmail.com>
 *
 * This file is part of Easer.
 *
 * Easer is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Easer is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Easer.  If not, see <http://www.gnu.org/licenses/>.
 */

package ryey.easer.skills.operation.hotspot;

import android.Manifest;
import android.app.Activity;
import android.content.Context;

import androidx.annotation.NonNull;

import ryey.easer.R;
import ryey.easer.commons.local_skill.SkillView;
import ryey.easer.commons.local_skill.operationskill.OperationDataFactory;
import ryey.easer.commons.local_skill.operationskill.OperationSkill;
import ryey.easer.commons.local_skill.operationskill.PrivilegeUsage;
import ryey.easer.plugin.operation.Category;
import ryey.easer.skills.operation.OperationLoader;
import ryey.easer.skills.SkillHelper;

public class HotspotOperationSkill implements OperationSkill<HotspotOperationData> {

    @NonNull
    @Override
    public String id() {
        return "hotspot";
    }

    @Override
    public int name() {
        return R.string.operation_hotspot;
    }

    @Override
    public boolean isCompatible(@NonNull final Context context) {
        return true;
    }

    @NonNull
    @Override
    public PrivilegeUsage privilege() {
        return PrivilegeUsage.no_root;
    }

    @Override
    public int maxExistence() {
        return 1;
    }

    @NonNull
    @Override
    public Category category() {
        return Category.system_config;
    }

    @Override
    public boolean checkPermissions(@NonNull Context context) {
        return SkillHelper.checkPermission(context,
                Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.CHANGE_WIFI_STATE);
    }

    @Override
    public void requestPermissions(@NonNull Activity activity, int requestCode) {
        boolean can_access_wifi = SkillHelper.checkPermission(activity, Manifest.permission.ACCESS_WIFI_STATE);
        boolean can_change_wifi = SkillHelper.checkPermission(activity, Manifest.permission.CHANGE_WIFI_STATE);
        if (!can_access_wifi && !can_change_wifi) {
            SkillHelper.requestPermission(activity, requestCode,
                    Manifest.permission.ACCESS_WIFI_STATE,
                    Manifest.permission.CHANGE_WIFI_STATE);
        } else if (!can_access_wifi) {
            SkillHelper.requestPermission(activity, requestCode,
                    Manifest.permission.ACCESS_WIFI_STATE);
        } else {
            SkillHelper.requestPermission(activity, requestCode,
                    Manifest.permission.CHANGE_WIFI_STATE);
        }
    }

    @NonNull
    @Override
    public OperationDataFactory<HotspotOperationData> dataFactory() {
        return new HotspotOperationDataFactory();

    }

    @NonNull
    @Override
    public SkillView<HotspotOperationData> view() {
        return new HotspotSkillViewFragment();
    }

    @NonNull
    @Override
    public OperationLoader<HotspotOperationData> loader(@NonNull Context context) {
        return new HotspotLoader(context);
    }

}
