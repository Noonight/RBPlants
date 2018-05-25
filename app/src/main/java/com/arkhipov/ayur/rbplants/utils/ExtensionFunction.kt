import android.support.design.widget.Snackbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class ExtensionFunction {
    companion object
    {
        fun create(view: View, message: String, btnMessage: String, func: (View)-> Unit): Snackbar
        {
            return Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE)
                .setAction(btnMessage, View.OnClickListener(func))
        }

        /*fun SnackbarUtils.f(message: String, btnMessage: String, func: (View) -> Unit): SnackbarUtils
        {
            return make(view, message, LENGTH_INDEFINITE)
                .setAction(btnMessage, View.OnClickListener(func))
        }*/

        fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
            return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
        }
    }

}

