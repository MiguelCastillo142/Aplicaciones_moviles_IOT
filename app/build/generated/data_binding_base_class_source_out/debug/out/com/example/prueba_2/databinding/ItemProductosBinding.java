// Generated by view binder compiler. Do not edit!
package com.example.prueba_2.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.prueba_2.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ItemProductosBinding implements ViewBinding {
  @NonNull
  private final MaterialCardView rootView;

  @NonNull
  public final MaterialButton btnEditaritem;

  @NonNull
  public final MaterialButton btnEliminaritem;

  @NonNull
  public final TextView tvDescripcion;

  @NonNull
  public final TextView tvNombre;

  private ItemProductosBinding(@NonNull MaterialCardView rootView,
      @NonNull MaterialButton btnEditaritem, @NonNull MaterialButton btnEliminaritem,
      @NonNull TextView tvDescripcion, @NonNull TextView tvNombre) {
    this.rootView = rootView;
    this.btnEditaritem = btnEditaritem;
    this.btnEliminaritem = btnEliminaritem;
    this.tvDescripcion = tvDescripcion;
    this.tvNombre = tvNombre;
  }

  @Override
  @NonNull
  public MaterialCardView getRoot() {
    return rootView;
  }

  @NonNull
  public static ItemProductosBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ItemProductosBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.item_productos, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ItemProductosBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnEditaritem;
      MaterialButton btnEditaritem = ViewBindings.findChildViewById(rootView, id);
      if (btnEditaritem == null) {
        break missingId;
      }

      id = R.id.btnEliminaritem;
      MaterialButton btnEliminaritem = ViewBindings.findChildViewById(rootView, id);
      if (btnEliminaritem == null) {
        break missingId;
      }

      id = R.id.tvDescripcion;
      TextView tvDescripcion = ViewBindings.findChildViewById(rootView, id);
      if (tvDescripcion == null) {
        break missingId;
      }

      id = R.id.tvNombre;
      TextView tvNombre = ViewBindings.findChildViewById(rootView, id);
      if (tvNombre == null) {
        break missingId;
      }

      return new ItemProductosBinding((MaterialCardView) rootView, btnEditaritem, btnEliminaritem,
          tvDescripcion, tvNombre);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
