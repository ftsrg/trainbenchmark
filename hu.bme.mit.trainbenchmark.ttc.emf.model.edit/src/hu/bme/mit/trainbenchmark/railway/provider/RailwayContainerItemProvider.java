/**
 */
package hu.bme.mit.trainbenchmark.railway.provider;


import hu.bme.mit.trainbenchmark.railway.RailwayContainer;
import hu.bme.mit.trainbenchmark.railway.RailwayFactory;
import hu.bme.mit.trainbenchmark.railway.RailwayPackage;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link hu.bme.mit.trainbenchmark.railway.RailwayContainer} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class RailwayContainerItemProvider 
	extends ItemProviderAdapter
	implements
		IEditingDomainItemProvider,
		IStructuredItemContentProvider,
		ITreeItemContentProvider,
		IItemLabelProvider,
		IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RailwayContainerItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

		}
		return itemPropertyDescriptors;
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(RailwayPackage.Literals.RAILWAY_CONTAINER__INVALIDS);
			childrenFeatures.add(RailwayPackage.Literals.RAILWAY_CONTAINER__SEMAPHORES);
			childrenFeatures.add(RailwayPackage.Literals.RAILWAY_CONTAINER__ROUTES);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns RailwayContainer.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/RailwayContainer"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		return getString("_UI_RailwayContainer_type");
	}
	

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(RailwayContainer.class)) {
			case RailwayPackage.RAILWAY_CONTAINER__INVALIDS:
			case RailwayPackage.RAILWAY_CONTAINER__SEMAPHORES:
			case RailwayPackage.RAILWAY_CONTAINER__ROUTES:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add
			(createChildParameter
				(RailwayPackage.Literals.RAILWAY_CONTAINER__INVALIDS,
				 RailwayFactory.eINSTANCE.createSegment()));

		newChildDescriptors.add
			(createChildParameter
				(RailwayPackage.Literals.RAILWAY_CONTAINER__INVALIDS,
				 RailwayFactory.eINSTANCE.createSwitch()));

		newChildDescriptors.add
			(createChildParameter
				(RailwayPackage.Literals.RAILWAY_CONTAINER__INVALIDS,
				 RailwayFactory.eINSTANCE.createRoute()));

		newChildDescriptors.add
			(createChildParameter
				(RailwayPackage.Literals.RAILWAY_CONTAINER__INVALIDS,
				 RailwayFactory.eINSTANCE.createSemaphore()));

		newChildDescriptors.add
			(createChildParameter
				(RailwayPackage.Literals.RAILWAY_CONTAINER__INVALIDS,
				 RailwayFactory.eINSTANCE.createSwitchPosition()));

		newChildDescriptors.add
			(createChildParameter
				(RailwayPackage.Literals.RAILWAY_CONTAINER__INVALIDS,
				 RailwayFactory.eINSTANCE.createSensor()));

		newChildDescriptors.add
			(createChildParameter
				(RailwayPackage.Literals.RAILWAY_CONTAINER__SEMAPHORES,
				 RailwayFactory.eINSTANCE.createSemaphore()));

		newChildDescriptors.add
			(createChildParameter
				(RailwayPackage.Literals.RAILWAY_CONTAINER__ROUTES,
				 RailwayFactory.eINSTANCE.createRoute()));
	}

	/**
	 * This returns the label text for {@link org.eclipse.emf.edit.command.CreateChildCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getCreateChildText(Object owner, Object feature, Object child, Collection<?> selection) {
		Object childFeature = feature;
		Object childObject = child;

		boolean qualify =
			childFeature == RailwayPackage.Literals.RAILWAY_CONTAINER__INVALIDS ||
			childFeature == RailwayPackage.Literals.RAILWAY_CONTAINER__ROUTES ||
			childFeature == RailwayPackage.Literals.RAILWAY_CONTAINER__SEMAPHORES;

		if (qualify) {
			return getString
				("_UI_CreateChild_text2",
				 new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
		}
		return super.getCreateChildText(owner, feature, child, selection);
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return RailwayEditPlugin.INSTANCE;
	}

}
